package ro.unibuc.coman.licenta.decisioning.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {
    @GetMapping("/hello")
    fun helloKotlin(): String {
        return "hello world"
    }
}