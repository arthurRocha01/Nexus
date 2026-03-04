from pydantic import BaseModel, Field
from typing import List, Optional

class SimpleProductItem(BaseModel):
  id: str
  description: str
  cost_price: Optional[float] = None

class FuzzyMatchRequest(BaseModel):
  products_received: List[SimpleProductItem] = Field(..., description='Produtos sem preço de custo')
  products_for_comparsion: List[SimpleProductItem] = Field(..., description='Produtos com preço de custo')
  min_score_threshold: float = Field(80.0, description='Nível de exigência do match (0 a 100)')

class MatchSuggestion(BaseModel):
  product_id: str
  corresponding_product_id: str
  product_description: str
  corresponding_product_description: str
  similarity_score: float
  suggested_cost_price: float

class FuzzyMatchResponse(BaseModel):
  suggestions: List[MatchSuggestion]