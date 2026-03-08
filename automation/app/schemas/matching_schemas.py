from pydantic import BaseModel, Field
from typing import Optional
from decimal import Decimal

class SimpleProductItem(BaseModel):
  id: str
  description: str
  quantity: int
  price: Decimal
  ncm: Optional[str]
  barcode: Optional[str]

class MatchSuggestion(BaseModel):
  compared_product: SimpleProductItem
  corresponding_product_name: str | None
  similarity_score: float | None