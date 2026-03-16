from fastapi import APIRouter
from pydantic.v1 import BaseModel
from app.schemas.matching_schemas import MatchSuggestion, SimpleProduct
from app.core.use_cases.fuzzy_matcher import find_similar_product
from pydantic import BaseModel

router = APIRouter()

class CheckPriceRequest(BaseModel):
  data: SimpleProduct

@router.post('/check-price', response_model=MatchSuggestion)
def check_price(
  request: CheckPriceRequest,
  min_score: float = 80
):
  return find_similar_product(request.data, min_score)