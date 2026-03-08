import json
from pathlib import Path
from rapidfuzz import process, fuzz
from app.schemas.matching_schemas import SimpleProductItem, MatchSuggestion

BASE_DIR = Path(__file__).resolve().parents[2]
FILE_PATH = BASE_DIR / "resources" / "gweb_export_old.json"

def __load_products_to_match():
  with open(FILE_PATH, 'r', encoding='utf-8') as file:
    data =  json.load(file)
  return data['products']

def __load_products_name_to_match():
  products_to_match = __load_products_to_match()
  return [p['name'] for p in products_to_match]

PRODUCTS_NAME_TO_MATCH = __load_products_name_to_match()

def __get_similar_product(product_for_comparison: SimpleProductItem, min_score):
  product_name_to_comparison = product_for_comparison.description

  match = process.extractOne(
    query=product_name_to_comparison,
    choices=PRODUCTS_NAME_TO_MATCH,
    scorer=fuzz.token_sort_ratio,
  )

  return match

def find_similar_product(
    product_for_comparison: SimpleProductItem,
  min_score: float = 80
) -> MatchSuggestion:
  match = __get_similar_product(product_for_comparison, min_score)
  
  if match is None:
    return MatchSuggestion(
      compared_product=product_for_comparison,
      corresponding_product_name=None,
      similarity_score=None
    )
  
  return MatchSuggestion(
    compared_product=product_for_comparison,
    corresponding_product_name=match[0],
    similarity_score=match[1]
  )