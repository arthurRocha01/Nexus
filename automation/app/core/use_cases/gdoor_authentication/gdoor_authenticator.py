from playwright.async_api import async_playwright
from app.core.use_cases.gdoor_authentication import authentication_settings

async def fetch_jwt_from_browser():
    async with async_playwright() as p:
        browser = await p.chromium.launch(
            executable_path=authentication_settings.PATH_BROWSER,
            # channel='chrome',
            headless=True
        )

        context = await browser.new_context()
        page = await context.new_page()

        await page.goto(authentication_settings.URL_SITE)

        await page.fill(authentication_settings.EMAIL_FIELD, authentication_settings.EMAIL)
        await page.click(authentication_settings.FIELD_CONTINUE_LOGIN)

        await page.fill(authentication_settings.PASSWORD_FIELD, authentication_settings.PASSWORD)
        await page.click(authentication_settings.FIELD_CONTINUE_LOGIN)

        await page.wait_for_load_state("networkidle")

        await page.wait_for_function(
            f"() => localStorage.getItem('{authentication_settings.NAME_LOCAL_STORAGE_TOKEN}') !== null"
        )

        token = await page.evaluate(
            f"() => localStorage.getItem('{authentication_settings.NAME_LOCAL_STORAGE_TOKEN}')"
        )

        await browser.close()
        
        return token