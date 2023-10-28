package io.github.willyanto39.cryptoviewer;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface CryptocurrencyCacheRepository extends JpaRepository<CryptocurrencyCache, Integer> {
  Optional<CryptocurrencyCache> findByCryptocurrencyId(String cryptocurrencyId);
}
