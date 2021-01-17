package br.com.joaomassan.transapp.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

interface OperationTypeRepository extends JpaRepository<OperationType, Long> {}
