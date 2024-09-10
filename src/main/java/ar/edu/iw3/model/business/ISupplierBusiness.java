package ar.edu.iw3.model.business;

import java.util.List;

import ar.edu.iw3.model.Supplier;

public interface ISupplierBusiness {

    public List<Supplier> list() throws BusinessException;

    public Supplier load(long id) throws NotFoundException, BusinessException;

    public Supplier load(String supplier) throws NotFoundException, BusinessException;

    public Supplier add(Supplier supplier) throws FoundException, BusinessException;

    public Supplier update(Supplier supplier) throws NotFoundException, BusinessException;

    public void delete(Supplier supplier) throws NotFoundException, BusinessException;

    public void delete(long id) throws NotFoundException, BusinessException;
}