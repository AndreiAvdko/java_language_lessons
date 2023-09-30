## Конфигурирование Java-приложений
### Виды конфигураций java-приложений
1. Annotation-based
   - Java-based (@Bean)

  - Annotation-based (@Autowired, @Service, @Controller)

    ```java
    @Configuration
    class AppConfig {
        @Bean
        IPersonDao personDao(@Value("${db.url}") String dbUrl) {
            return new PersonDAO(dbUrl);
        }
        
        @Bean
        PersonService personService(IPersonDAO dao) {
            return new PersonService(dao);
        }
    }
    ```
    Использование бинов через контекст:
    ```java
        public class Main {
            public static void main(String[] args){
                AnnotationConfigApplicationContext context = 
                    new AnnotationConfigApplicationContext(AppConfig.class);
                
                PersonService service = context.getBean(PersonService.class);
                Person ivan = service.getByName("Ivan");
            }
        }   
    ```
2. XML-based
    ```java
    <beans>
        <bean id="personDao" class="ru.otus.spring.dao.PersonDao">
            <constructor-arg name="dbUrl" value="${db.url}"/> 
        </bean>
   
        <bean id="personService" class="ru.otus.spring.service.PersonSevice">
            <constructor-arg name="dao" ref="personDao"/>
        </bean>
   </beans>
   ```
   
Ограничения:
1. Класс конфигурации должен иметь конструктор без параметров (обычно его просто не пишут - он уже есть)
2. Могут содержать @Autowired поля, которые потом можно использовать в методах
3. Методы - нестатические, генерируют бины, помеченные аннотацией @Bean

## Аннотация `@Configuration`
Этой аннотацией помечены другие аннотацией помечены другие аннотации:
- `@SpringBootApplication`
- `@TestConfiguration`
- `@EnableWebSecurity`

Конфигурационный класс может наследоваться от специальных Spring-конфигурационных файлов.

## Import конфигурации

```java

Без импорта при создании контекста будет доступен только один класс:
@Import(OtherConfig.class)
@Configuration
class AppConfig {
    @Bean
    // ...
}
```

__@ComponentScan__. Динамическое изменение контекста:
```java
@ComponentScan(basePackages="ru.otus")
@Configuration      // Необязательная аннотация  
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        
        ctx.register(Application.class);
        ctx.refresh();
        ctx.getBean(PersonService.class);
    }
}
```
Особенности `@ComponentScan`:
- Ищет классы конфигураций
- Ищет классы, помеченные стереотипами `@Service`, `@Controller`
- Если не задан package - ищет по пакетам "вглубь", начиная с текущего
- Не ищет интерфейсы!!! (Spring Data, Spring Integrations имеют свои аннотации, которые надо добавлять дополнительно)

