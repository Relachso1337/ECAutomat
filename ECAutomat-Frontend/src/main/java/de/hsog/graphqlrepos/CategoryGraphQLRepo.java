//package de.hsog.graphqlrepos;
//
//import java.util.List;
//
//import org.springframework.graphql.client.GraphQlClient;
//import org.springframework.graphql.client.HttpGraphQlClient;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import de.hsog.dto.Category;
//import de.hsog.dto.Question;
//import reactor.core.publisher.Mono;
//
//public class CategoryGraphQLRepo {
//
//	private final String serverAddress;
//	private final int serverPort;
//	private final String graphQLContext;
//
//	public CategoryGraphQLRepo() {
//		// http://localhost:8888/api/v1/graphql
//		this.serverAddress = "http://localhost";
//		this.serverPort = 8888;
//		this.graphQLContext = "/api/v1/graphql";
//	}
//	
//	public CategoryGraphQLRepo(String address, int port, String context) {
//		this.serverAddress = address;
//		this.serverPort = port;
//		this.graphQLContext = context;
//	}
//	
//	record CategoryOutput(int id, String categoryName, List<Question> questions) {};
//	
//	public Category getCategoryById(int id) {
//		String doc = String.format("""
//				{
//					categoryById(id: %d) {
//						id
//						categoryName
//						questions {
//							id
//						}
//					}
//				}
//				""", id);
//		Category cat = new Category();
//		WebClient client = WebClient.create(
//											String.format(
//														"%s:%d%s", 
//															this.serverAddress, 
//															this.serverPort, 
//															this.graphQLContext)
//											);
//		GraphQlClient graphQlClient = HttpGraphQlClient.create(client);
//		Mono<Category> category = graphQlClient
//									.document(doc)
//									.retrieve("categoryById")
//									.toEntity(Category.class);
//		category.subscribe(r -> {
//			cat.setId(r.getId());
//			cat.setCategoryName(r.getCategoryName());
//			cat.setQuestions(r.getQuestions());
//		});
//		return cat;
//	}
//
//}
