package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import uz.ws.munis.GetCountryRequest;
import uz.ws.munis.GetCountryResponse;
import uz.ws.munis.GetSubject;
import uz.ws.munis.GetSubjectResponse;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://munis.ws.uz";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSubject")
	@ResponsePayload
	public GetSubjectResponse getSubject(@RequestPayload GetSubject request) {
		GetSubjectResponse res = new GetSubjectResponse();
		res.setRes(request.getReq());
		return res;
	}
}
