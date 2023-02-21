package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.repository.Shop.CarteRepository;

@Service
@Slf4j
public class CartService implements ICartService {

    @Autowired
    private CarteRepository carteRepository;


}
