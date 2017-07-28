package connect.reservation.service;

import connect.reservation.domain.File;

public interface FileService {
	public File get(int id);
	public int add(int commentId, File file);
}
