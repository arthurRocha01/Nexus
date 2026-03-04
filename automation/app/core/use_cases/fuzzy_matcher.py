from rapidfuzz import process, fuzz
from typing import List
from app.schemas.matching_schemas import SimpleProductItem, MatchSuggestion

def find_similar_products(
    products_received: List[SimpleProductItem],
    products_for_comparsion: List[SimpleProductItem],
    min_score_threshold: float
) -> List[MatchSuggestion]:
  
  suggestions = []

  dict_products_for_comparsion = {p.description: p for p in products_for_comparsion}
  descriptions_products_for_comparsion = list(dict_products_for_comparsion.keys())

  for product_received in products_received:
    result = process.extractOne(
      query=product_received.description,
      choices=descriptions_products_for_comparsion,
      scorer= fuzz.token_sort_ratio
    )

    if result:
      best_description_match, matching_score, _ = result

      if matching_score >= min_score_threshold:
        product_received_corresponds = dict_products_for_comparsion[best_description_match]

        if product_received_corresponds.cost_price is not None and product_received_corresponds.cost_price > 0:
          suggestions.append(
            MatchSuggestion(
              product_id=product_received.id,
              corresponding_product_id=product_received_corresponds.id,
              product_description=product_received.description,
              corresponding_product_description=product_received_corresponds.description,
              similarity_score=round(matching_score, 2),
              suggested_cost_price=product_received_corresponds.cost_price
            )
          )

  return suggestions