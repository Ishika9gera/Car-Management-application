package com.ishika.dataaccessobject;

import com.ishika.domainobject.DriverDO;
import com.ishika.domainvalue.OnlineStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long> {

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    List<DriverDO> findAllByUsernameIsContaining(String username);

    List<DriverDO> findAllByOnlineStatusEqualsAndUsernameIsContaining(OnlineStatus onlineStatus, String username);

    List<DriverDO> findAllBySelectedCarNotNull();

}
