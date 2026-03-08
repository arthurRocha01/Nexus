from fastapi import FastAPI
from app.api.routes import inventory_routes
from app.api.routes import auth_routes

app = FastAPI(
  title='Nexus Automation API',
  description='Motor de processamento de dados para o sistema Nexus'
)

app.include_router(inventory_routes.router, prefix='/api/inventory', tags=['Inventory Matching'])
app.include_router(auth_routes.router, prefix='/api/auth', tags=['Authentication'])

@app.get('/health')
def health_check():
  return {'status': 'up', 'message': 'Automation Engine is running'}