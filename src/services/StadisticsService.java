package services;


import repository.ProductRepository;

import java.util.Arrays;

public class StadisticsService {
    private ProductRepository productRepository;

    public String getPriceMinAndMax(){
        return Arrays.stream(productRepository.getPrices())
                .max()
                .stream().min()
                .toString();
    }
}
