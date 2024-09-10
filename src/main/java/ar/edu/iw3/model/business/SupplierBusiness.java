package ar.edu.iw3.model.business;

import ar.edu.iw3.model.Supplier;
import ar.edu.iw3.model.persistence.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SupplierBusiness implements ISupplierBusiness {
    @Autowired
    private SupplierRepository supplierDAO;

    @Override
    public List<Supplier> list() throws BusinessException {
        try {
            return supplierDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Supplier load(long id) throws NotFoundException, BusinessException {
        try {
            return supplierDAO.findById(id).orElseThrow(()
                    -> NotFoundException.builder()
                    .message("No se encuentra el Proveedor id=" + id).build());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Supplier load(String supplier) throws NotFoundException, BusinessException {
        try {
            return supplierDAO.findOneBySupplier(supplier).orElseThrow(()
                    -> NotFoundException.builder()
                    .message("No se encuentra el Proveedor=" + supplier).build());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Supplier add(Supplier supplier) throws FoundException, BusinessException {
        try {
            load(supplier.getId());
            throw FoundException.builder().message("Ya existe el Proveedor id=" + supplier.getId()).build();
        } catch (NotFoundException ignored) {

        }

        try {
            load(supplier.getSupplier());
            throw FoundException.builder().message("Ya existe el Proveedor=" + supplier.getSupplier()).build();
        } catch (NotFoundException ignored) {

        }

        try {
            return supplierDAO.save(supplier);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw BusinessException.builder().ex(ex).build();
        }
    }


    @Override
    public Supplier update(Supplier supplier) throws NotFoundException, BusinessException {
        // Verificamos que exista el proveedor
        Supplier existingSupplier = load(supplier.getId());

        Optional<Supplier> supplierByName = supplierDAO.findOneBySupplier(supplier.getSupplier());
        if (supplierByName.isPresent() && supplierByName.get().getId() != supplier.getId()) {
            throw BusinessException.builder().
                    message("Ya existe el Proveedor " + supplier.getSupplier() +
                            " bajo el ID " + supplier.getId()).build();
        }

        try {
            return supplierDAO.save(supplier);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public void delete(Supplier supplier) throws NotFoundException, BusinessException {
        delete(supplier.getId());
    }

    @Override
    public void delete(long id) throws NotFoundException, BusinessException {
        load(id);
        try {
            supplierDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }
}
