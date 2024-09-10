package ar.edu.iw3.model.business;

import ar.edu.iw3.model.Category;
import ar.edu.iw3.model.persistence.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CategoryBusiness implements ICategoryBusiness {

    @Autowired
    private CategoryRepository categoryDAO;

    @Override
    public Category load(long id) throws NotFoundException, BusinessException {
        Optional<Category> r;

        try {
            r = categoryDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty())
            throw NotFoundException.builder().message("No se encuentra la Categoria id=" + id).build();

        return r.get();
    }

    @Override
    public Category load(String category) throws NotFoundException, BusinessException {
        Optional<Category> r;

        try {
            r = categoryDAO.findOneByCategory(category);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty())
            throw NotFoundException.builder().message("No se encuentra la Categoria=" + category).build();

        return r.get();
    }

    @Override
    public List<Category> list() throws BusinessException {
        try {
            return categoryDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Category add(Category category) throws FoundException, BusinessException {
        try {
            load(category.getId());
            throw FoundException.builder().message("Ya existe la Categoria id=" + category.getId()).build();
        } catch (NotFoundException ignored) {
        }

        try {
            load(category.getCategory());
            throw FoundException.builder().message("Ya existe la Categoria " + category.getCategory()).build();
        } catch (NotFoundException ignored) {
        }

        try {
            return categoryDAO.save(category);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw BusinessException.builder().ex(ex).build();
        }
    }

    @Override
    public Category update(Category category) throws NotFoundException, BusinessException {
        Category existentCategory = load(category.getId());

        Optional<Category> categoryByName = categoryDAO.findOneByCategory(category.getCategory());
        if (categoryByName.isPresent() && !Objects.equals(categoryByName.get().getId(), category.getId())) {
            throw BusinessException.builder().message("Ya existe la Categoria " + category.getCategory() +
                    " bajo el ID " + category.getId()).build();
        }

        try {
            return categoryDAO.save(category);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw BusinessException.builder().ex(ex).build();
        }
    }

    @Override
    public void delete(Category category) throws NotFoundException, BusinessException {
        delete(category.getId());
    }

    @Override
    public void delete(long id) throws NotFoundException, BusinessException {
        load(id);
        try {
            categoryDAO.deleteById(id);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw BusinessException.builder().ex(ex).build();
        }
    }
}
