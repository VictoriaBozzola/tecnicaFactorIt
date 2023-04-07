package com.example.Backendtecnica;

import com.example.Backendtecnica.entities.FechaEspecial;
import com.example.Backendtecnica.entities.UsuarioGenerate;
import com.example.Backendtecnica.repository.FechaRepository;
import com.example.Backendtecnica.repository.ProductoRespository;
import com.example.Backendtecnica.entities.Producto;
import com.example.Backendtecnica.repository.UsuariosRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class BackendTecnicaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BackendTecnicaApplication.class, args);

        ProductoRespository repository =   context.getBean(ProductoRespository.class);
        UsuariosRepository usuariosRepository = context.getBean(UsuariosRepository.class);
        FechaRepository fechaRepository = context.getBean(FechaRepository.class);

        Producto producto1 = new Producto(null, "Cama king", 10000);
        Producto producto2 = new Producto(null, "Lampara", 50);
        UsuarioGenerate usuario1 = new UsuarioGenerate(null, "Victoria", true);
        UsuarioGenerate usuario2 = new UsuarioGenerate(null, "Maria", false);
        FechaEspecial fechaPromocion = new FechaEspecial(null, LocalDate.of(2023, 4, 22));

        //Mock DB
        repository.save(producto1);
        repository.save(producto2);
        usuariosRepository.save(usuario1);
        usuariosRepository.save(usuario2);
        fechaRepository.save(fechaPromocion);
	}

}
