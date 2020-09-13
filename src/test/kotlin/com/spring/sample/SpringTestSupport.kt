//package com.spring.sample
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import io.micrometer.core.instrument.util.IOUtils
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.TestInstance
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.io.ResourceLoader
//import org.springframework.restdocs.RestDocumentationContextProvider
//import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
//import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
//import org.springframework.restdocs.operation.preprocess.Preprocessors
//import org.springframework.test.context.TestConstructor
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers
//import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
//import org.springframework.test.web.servlet.setup.MockMvcBuilders
//import org.springframework.web.context.WebApplicationContext
//import org.springframework.web.filter.CharacterEncodingFilter
//import java.nio.charset.StandardCharsets
//import javax.persistence.EntityManager
//
//@SpringBootTest(properties = ["spring.profiles.active=test"])
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
//class SpringTestSupport {
//
//    @Autowired
//    private lateinit var entityManager: EntityManager
//
//
//    protected fun <T> save(entity: T): T {
//        entityManager.persist(entity)
//        flushAndClearPersistentContext()
//        return entity
//    }
//
//    protected fun <T> saveAll(entities: Iterable<T>): Iterable<T> {
//        for (entity in entities) {
//            entityManager.persist(entity)
//        }
//        flushAndClearPersistentContext()
//        return entities
//    }
//
//    private fun flushAndClearPersistentContext() {
//        entityManager.flush()
//        entityManager.clear()
//    }
//}
//
//
//@AutoConfigureMockMvc
//class SpringWebTestSupport : SpringTestSupport() {
//
//    @Autowired
//    protected lateinit var objectMapper: ObjectMapper
//
//    @Autowired
//    protected lateinit var restdocs: RestDocsConfiguration
//
//    @Autowired
//    protected lateinit var mockMvc: MockMvc
//
//    @Autowired
//    protected lateinit var write: RestDocumentationResultHandler
//
//    @Autowired
//    protected lateinit var resourceLoader: ResourceLoader
//
//    protected val classpath = "classpath:"
//
//    @BeforeEach
//    fun setUp(context: WebApplicationContext, provider: RestDocumentationContextProvider) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
//            .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(provider))
//            .addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
//            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
//            .alwaysDo<DefaultMockMvcBuilder>(restdocs.write())
//            .build()
//    }
//
//
//    protected fun readFile(path: String): String {
//        val inputStream = resourceLoader.getResource(classpath + path).inputStream
//        return IOUtils.toString(inputStream, StandardCharsets.UTF_8)
//    }
//}
//
//@Configuration
//class RestDocsConfiguration {
//    @Bean
//    fun write(): RestDocumentationResultHandler {
//        return MockMvcRestDocumentation.document(
//            "{class-name}/{method-name}",
//            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
//            Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
//        )
//    }
//}