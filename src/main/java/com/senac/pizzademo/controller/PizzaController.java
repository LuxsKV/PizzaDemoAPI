package com.senac.pizzademo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senac.pizzademo.model.Pizza;
import com.senac.pizzademo.repository.PizzaRepository;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public List<Pizza> listarPizzas() {
        return pizzaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> buscarPizza(@PathVariable Long id) {
        return pizzaRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pizza criarPizza(@RequestBody Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> atualizarPizza(@PathVariable Long id, @RequestBody Pizza pizzaAtualizada) {
        return pizzaRepository.findById(id).map(pizza -> {
            pizza.setNome(pizzaAtualizada.getNome());
            pizza.setDescricao(pizzaAtualizada.getDescricao());
            pizza.setPreco(pizzaAtualizada.getPreco());
            pizza.setImagem(pizzaAtualizada.getImagem());
            return ResponseEntity.ok(pizzaRepository.save(pizza));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPizza(@PathVariable Long id) {
        return pizzaRepository.findById(id).map(pizza -> {
            pizzaRepository.delete(pizza);
            return ResponseEntity.noContent().<Void>build();
    }).orElse(ResponseEntity.notFound().build());
}

}
