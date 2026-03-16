import json
from pathlib import Path
from rapidfuzz import process, fuzz
from app.schemas.matching_schemas import SimpleProduct, MatchSuggestion
from decimal import Decimal

BASE_DIR = Path(__file__).resolve().parents[2]
FILE_PATH = BASE_DIR / "resources" / "gweb_export_old.json"

def __load_products_data():
  with open(FILE_PATH, 'r', encoding='utf-8') as file:
    data =  json.load(file)
  products = data.get('products', [])
  return {p['name']: p for p in products}

PRODUCTS_DICT = __load_products_data()
PRODUCTS_NAME = list(PRODUCTS_DICT.keys())

def __map_to_simple_product(data) -> SimpleProduct:
    details = data.get("details", [{}])[0]
    barcodes = details.get("barcodes", [])
    
    if barcodes and isinstance(barcodes[0], dict):
        first_barcode = barcodes[0].get("barcode")
    else:
        first_barcode = None

    return SimpleProduct(
        id=str(data.get("old_id")),
        description=data.get("name"),
        quantity=int(details.get("current_quantity", 0)),
        price=Decimal(str(details.get("price", 0))),
        costPrice=Decimal(str(details.get("cost_price", 0))),
        ncm=str(details.get("ncm_code", "")),
        barcode=first_barcode
    )

def find_similar_product(
    product_for_comparison: SimpleProduct,
  min_score: float = 80
) -> MatchSuggestion:
  match = process.extractOne(
    query=product_for_comparison.description,
    choices=PRODUCTS_NAME,
    scorer=fuzz.token_sort_ratio
  )
  
  if match is None:
    return MatchSuggestion(
      compared_product=product_for_comparison,
      corresponding_product=None,
      similarity_score=None
    )
  
  matched_name = match[0]
  similarity_score = match[1]

  raw_product_data = PRODUCTS_DICT.get(matched_name)
  corresponding_product = __map_to_simple_product(raw_product_data)
  
  return MatchSuggestion(
    compared_product=product_for_comparison,
    corresponding_product=corresponding_product,
    similarity_score=similarity_score
  )