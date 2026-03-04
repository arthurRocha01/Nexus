from pydantic import BaseModel

class GdoorAuthResponse(BaseModel):
  jwt_token: str
  message: str