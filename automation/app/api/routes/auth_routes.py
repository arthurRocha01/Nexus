from fastapi import APIRouter, HTTPException
from app.schemas.auth_schema import GdoorAuthResponse
from app.core.use_cases.gdoor_authentication.gdoor_authenticator import fetch_jwt_from_browser

router = APIRouter()

@router.get('/gdoor-token', response_model=GdoorAuthResponse)
async def get_gdoor_token():
  try: 
    token = await fetch_jwt_from_browser()
    print(f'Token: {token}')
    return GdoorAuthResponse(jwt_token=token, message='Token Capturado com sucesso')
  except Exception as e:
    raise HTTPException(status_code=500, detail=f'Falha na automação de login: {str(e)}')