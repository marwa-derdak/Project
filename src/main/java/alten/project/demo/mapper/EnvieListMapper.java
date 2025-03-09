package alten.project.demo.mapper;

import alten.project.demo.dto.responses.EnvieListResponse;
import alten.project.demo.model.EnvieList;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EnvieListMapper {

    EnvieListResponse envielistToEnvielistResponse(EnvieList envieList);

}
