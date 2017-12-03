package br.com.facial.notifications;

import javax.inject.Inject;

import br.com.facial.persistence.AbstractService;

public class NotificationsService extends AbstractService<Notifications, NotificationsRepo> {

	@Inject
	private NotificationsRepo notificationsRepo;

	@Override
	protected NotificationsRepo getRepo() {
		return notificationsRepo;
	}

}
