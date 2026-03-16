const fetchProductByBarcode = async (barcode) => {
    const response = await fetch(`/api/products/search?barcode=${barcode}`);
    if (!response.ok) throw new Error("Erro ao localizar barcode");
    const data = await response.json();
    return data?.[0];
}

export const fetchCheckPrice = async (barcode) => {
    try {
        const summary = await fetchProductByBarcode(barcode);
        if (!summary) return null;

        const { id } = summary;

        const response = await fetch(`/api/products/${id}/check-price`);

        if (!response.ok) {
            console.log("Produto encontrado, mas não foi possível obter sugestões de preço.");
            return {
                originalProduct: summary,
                matchedProduct: null,
                similarity_score: 0,
            }
        }

        const suggestions = await response.json();

        return {
            originalProduct: suggestions.originalProduct,
            matchedProduct: suggestions.matchedProduct,
            similarityScore: suggestions.similarityScore,
        };
    } catch (error) {
        console.error("Erro no fluxo de busca:", error);
        return null;
    }
}