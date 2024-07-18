import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter

@Configuration
class SerializationConfig {

    @Bean
    fun kotlinJson(): Json {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    @Bean
    fun kotlinSerializationJsonHttpMessageConverter(json: Json): KotlinSerializationJsonHttpMessageConverter {
        return KotlinSerializationJsonHttpMessageConverter(json)
    }
}
