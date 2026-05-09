package be.iccbxl.pid.youforbel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.iccbxl.pid.youforbel.model.Type;
import be.iccbxl.pid.youforbel.repository.TypeRepository;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public Type getTypeById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type introuvable id=" + id));
    }
}