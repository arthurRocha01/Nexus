from fastapi import APIRouter
from app.schemas.matching_schemas import FuzzyMatchRequest, FuzzyMatchResponse
from app.core.use_cases.fuzzy_matcher import find_similar_products

router = APIRouter()

@router.post('/fuzzy-match', response_model=FuzzyMatchResponse)
def match_inventory(payload: FuzzyMatchRequest):
  sugestions = find_similar_products(
    products_received=payload.products_received,
    products_for_comparsion=payload.products_for_comparsion,
    min_score_threshold=payload.min_score_threshold
  )

  return FuzzyMatchResponse(suggestions=sugestions)