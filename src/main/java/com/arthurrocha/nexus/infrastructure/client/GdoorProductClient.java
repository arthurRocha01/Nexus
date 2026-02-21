package com.arthurrocha.nexus.infrastructure.client;

public class GdoorProductClient {
  
}

// @FeignClient(
//     name = "gdoorProductClient", 
//     url = "${gdoor.api.url}", 
//     configuration = GdoorClientConfig.class
// )
// public interface GdoorProductClient {

//     // Aqui ficam APENAS os endpoints!
//     @GetMapping("/produtos/{id}")
//     Product buscarProduto(@PathVariable("id") String id);

// }