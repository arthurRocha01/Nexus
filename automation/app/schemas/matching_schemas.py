from pydantic import BaseModel, Field
from typing import Optional
from decimal import Decimal

class SimpleProduct(BaseModel):
  id: str
  description: str
  quantity: int
  price: Decimal
  costPrice: Decimal
  ncm: Optional[str]
  barcode: Optional[str]

class MatchSuggestion(BaseModel):
  compared_product: SimpleProduct
  corresponding_product: SimpleProduct | None
  similarity_score: float | None