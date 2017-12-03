package br.com.facial.notifications;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import br.com.facial.persistence.AbstractService;

@Component
@Path("notifications")
public class NotificationsService extends AbstractService<Notifications, NotificationsRepo> {

	@Inject
	private NotificationsRepo notificationsRepo;

	@Override
	protected NotificationsRepo getRepo() {
		return notificationsRepo;
	}

}
