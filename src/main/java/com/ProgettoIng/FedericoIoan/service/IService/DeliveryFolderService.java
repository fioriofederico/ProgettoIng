package com.ProgettoIng.FedericoIoan.service.IService;

import com.ProgettoIng.FedericoIoan.model.DeliveryFolder;

import com.ProgettoIng.FedericoIoan.model.dto.DeliveryFolderDto;

import java.util.List;

public interface DeliveryFolderService {

    DeliveryFolder createDeliveryFolder(DeliveryFolderDto deliveryFolder);

    List<DeliveryFolder> findDeliveryFolders(Long courseId);

    DeliveryFolder findDeliveryFolder(Long id);

    DeliveryFolder updateDeliveryFolder(Long id, DeliveryFolderDto deliveryFolder);

    DeliveryFolder deleteDeliveryFolder(Long id);
}
