package com.hady.usuario.business;

import com.hady.usuario.business.converter.UsuarioConverter;
import com.hady.usuario.business.dto.UsuarioDTO;
import com.hady.usuario.infrastructure.entity.Usuario;
import com.hady.usuario.infrastructure.exceptions.ConflictException;
import com.hady.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.hady.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final com.hady.usuario.infrastructure.security.JwtUtil jwtUtil;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
       Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
       return usuarioConverter.paraUsuarioDTO(
               usuarioRepository.save(usuario));
    }

    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictException("email ja cadastrado" + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado." + e.getCause());
        }
    }


    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail (String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email, não encontrado"+ email));
    }
    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail((email));

    }

    public UsuarioDTO atualizaDadosUsuario(String token ,UsuarioDTO dto){


        //busca email atravez do token (para tirar a obrigatoriedade do email no update)
       String email = jwtUtil.extractUsername(token.substring(7));

       //Criptografa a senha
       dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()): null);

       // busca os dados do usuario no banco de dados
       Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
               new ResourceNotFoundException("Email não localizado"));


       //Mescla os dados que recebemos na requisicao do DTO com o banco de dados
       Usuario usuario = usuarioConverter.updateUsuario(dto,usuarioEntity);



       //salva os dados do usuario convertido , pega o retorno e converte em usuarioDTO
       return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }

}
