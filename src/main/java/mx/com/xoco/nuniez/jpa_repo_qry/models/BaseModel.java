package mx.com.xoco.nuniez.jpa_repo_qry.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;

@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModels {
}
