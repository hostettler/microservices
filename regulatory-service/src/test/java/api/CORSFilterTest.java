package api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CORSFilterTest {

	@Test
	void testFilter() throws IOException {

		// create the objects to be mocked
		ContainerRequestContext requestContext = mock(ContainerRequestContext.class);
		ContainerResponseContext responseContext = mock(ContainerResponseContext.class);

		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		when(responseContext.getHeaders()).thenReturn(headers);

		CORSFilter corsFilter = new CORSFilter();
		corsFilter.filter(requestContext, responseContext);

		assertEquals("*", headers.get("Access-Control-Allow-Origin").get(0));
		assertEquals("GET, POST, PUT, DELETE, OPTIONS", headers.get("Access-Control-Allow-Methods").get(0));

	}

}
