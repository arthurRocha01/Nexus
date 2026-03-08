from fastapi import APIRouter
from app.schemas.matching_schemas import MatchSuggestion, SimpleProductItem
from app.core.use_cases.fuzzy_matcher import find_similar_product

router = APIRouter()

@router.post('/check-price', response_model=MatchSuggestion)
def check_price(
  payload: SimpleProductItem,
  min_score: float = 80
):
  return find_similar_product(payload, min_score)