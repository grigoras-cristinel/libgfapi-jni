/*
    Copyright (c) 2013 Louis Zuckerman All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are
    met:

       * Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
       * Redistributions in binary form must reproduce the above
    copyright notice, this list of conditions and the following disclaimer
    in the documentation and/or other materials provided with the
    distribution.
       * Neither the names of the authors nor the names of
    contributors may be used to endorse or promote products derived from
    this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
    "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
    LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
    A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
    OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
    SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
    LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
    THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
    OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.peircean.libgfapi_jni.internal;

import static org.fusesource.hawtjni.runtime.ArgFlag.NO_IN;
import static org.fusesource.hawtjni.runtime.ArgFlag.NO_OUT;

import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.Library;

import com.peircean.libgfapi_jni.internal.structs.dirent;
import com.peircean.libgfapi_jni.internal.structs.stat;
import com.peircean.libgfapi_jni.internal.structs.statvfs;
import com.peircean.libgfapi_jni.internal.structs.timespec;

/**
 * @author <a href="http://hiramchirino.com">Hiram Chirino</a>
 *         <a href="http://about.me/louiszuckerman">Louis Zuckerman</a>
 */
@JniClass
public class GLFS {

	public static final Library LIBRARY = new Library("libgfapi-jni", GLFS.class);

	static {
		GLFS.LIBRARY.load();
	}

	@JniMethod(cast = "glfs_t *")
	public static final native long glfs_new(@JniArg(cast = "const char*") String volname);

	@JniMethod
	public static final native int glfs_set_volfile_server(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char*") String transport, @JniArg(cast = "const char*") String host, int port);

	@JniMethod
	public static final native int glfs_set_logging(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char*") String logfile, int loglevel);

	@JniMethod
	public static final native int glfs_init(@JniArg(cast = "glfs_t *") long fs);

	@JniMethod
	public static final native int glfs_fini(@JniArg(cast = "glfs_t *") long fs);

	@JniMethod(cast = "glfs_fd_t *")
	public static final native long glfs_open(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char*") String path, int flags);

	@JniMethod(cast = "glfs_fd_t *")
	public static final native long glfs_creat(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char*") String path, int flags, int mode);

	@JniMethod
	public static final native int glfs_close(@JniArg(cast = "glfs_fd_t *") long fd);

	@JniMethod
	public static final native int glfs_fsync(@JniArg(cast = "glfs_fd_t *") long fd);

	@JniMethod(cast = "glfs_t *")
	public static final native long glfs_from_glfd(@JniArg(cast = "glfs_fd_t *") long fd);

	@JniMethod
	public static final native int glfs_write(@JniArg(cast = "glfs_fd_t *") long fd,
			@JniArg(cast = "const char*", flags = NO_OUT) byte[] buf, int count, int flags);

	@JniMethod(cast = "ssize_t")
	public static final native long glfs_read(@JniArg(cast = "glfs_fd_t *") long fd,
			@JniArg(cast = "void *", flags = NO_IN) byte[] buf, @JniArg(cast = "size_t") long count, int flags);

	@JniMethod(cast = "off_t")
	public static final native int glfs_lseek(@JniArg(cast = "glfs_fd_t *") long fd,
			@JniArg(cast = "off_t") long offset, int whence);

	@JniMethod
	public static final native int glfs_unlink(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path);

	// int glfs_statvfs (glfs_t *fs, const char *path, struct statvfs *buf);

	@JniMethod
	public static final native int glfs_statvfs(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path, @JniArg(flags = NO_IN) statvfs buf);

	// int glfs_stat (glfs_t *fs, const char *path, struct stat *buf);
	@JniMethod
	public static final native int glfs_stat(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path, @JniArg(flags = NO_IN) stat buf);

	// int glfs_lstat (glfs_t *fs, const char *path, struct stat *buf);
	@JniMethod
	public static final native int glfs_lstat(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path, @JniArg(flags = NO_IN) stat buf);

	// int glfs_fstat (glfs_fd_t *fd, struct stat *buf);
	@JniMethod
	public static final native int glfs_fstat(@JniArg(cast = "glfs_fd_t *") long fd, @JniArg(flags = NO_IN) stat buf);

	// int glfs_access (glfs_t *fs, const char *path, int mode);
	@JniMethod
	public static final native int glfs_access(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path, int mode);

	// int glfs_mkdir(glfs_t *fs, const char *path, int mode);
	@JniMethod
	public static final native int glfs_mkdir(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path, int mode);

	// glfs_fd_t *glfs_opendir (glfs_t *fs, const char *path);
	@JniMethod(cast = "glfs_fd_t *")
	public static final native long glfs_opendir(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path);

	// int glfs_readdir_r (glfs_fd_t *fd, struct dirent *dirent, struct dirent
	// **result);
	@JniMethod
	public static final native int glfs_readdir_r(@JniArg(cast = "glfs_fd_t *") long fd,
			@JniArg(flags = NO_IN) dirent dirent, @JniArg(cast = "struct dirent **") long result);

	// long glfs_telldir (glfs_fd_t *fd);
	@JniMethod
	public static final native long glfs_telldir(@JniArg(cast = "glfs_fd_t *") long fd);

	// void glfs_seekdir (glfs_fd_t *fd, long offset);
	@JniMethod
	public static final native void glfs_seekdir(@JniArg(cast = "glfs_fd_t *") long fd, long offset);

	// int glfs_closedir (glfs_fd_t *fd);
	@JniMethod
	public static final native int glfs_closedir(@JniArg(cast = "glfs_fd_t *") long fd);

	// int glfs_rmdir(glfs_t *fs, const char *path);
	@JniMethod
	public static final native int glfs_rmdir(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path);

	// int glfs_rename (glfs_t *fs, const char *oldpath, const char *newpath);
	@JniMethod
	public static final native int glfs_rename(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String oldpath, @JniArg(cast = "const char *") String newpath);

	// int glfs_chmod (glfs_t *fs, const char *path, int mode);
	public static final native int glfs_chmod(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path, int mode);

	// int glfs_symlink (glfs_t *fs, const char *oldpath, const char *newpath);
	@JniMethod
	public static final native int glfs_symlink(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String oldpath, @JniArg(cast = "const char *") String newpath);

	// int glfs_readlink (glfs_t *fs, const char *path, char *buf, size_t
	// bufsiz);
	@JniMethod
	public static final native int glfs_readlink(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path, @JniArg(cast = "char *", flags = NO_IN) byte[] buf,
			@JniArg(cast = "size_t") long bufsiz);

	/**
	 * Copy the Volume UUID stored in the glfs object fs.
	 *
	 * @param fs
	 *            he 'virtual mount' object to be used to retrieve and store
	 *            volume's UUID
	 * @param volid
	 *            Pointer to a place for the volume UUID to be stored
	 * @param bufsiz
	 *            Length of @volid
	 * @return length of the volume UUID stored
	 */
	// int glfs_get_volumeid (struct glfs *fs, char *volid, size_t size);
	@JniMethod
	public static final native int glfs_get_volumeid(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "char *", flags = NO_IN) byte[] volid, @JniArg(cast = "size_t") long bufsiz);

	// int glfs_setfsuid (uid_t fsuid) ;

	@JniMethod
	public static final native int glfs_setfsuid(@JniArg(cast = "uid_t", flags = ArgFlag.NO_OUT) long fsuid);

	// int glfs_setfsgid (gid_t fsgid) ;

	@JniMethod
	public static final native int glfs_setfsgid(@JniArg(cast = "gid_t", flags = ArgFlag.NO_OUT) long fsuid);

	// int glfs_setfsgroups (size_t size, const gid_t *list) ;
	@JniMethod
	public static final native int glfs_setfsgroups(@JniArg(cast = "size_t", flags = ArgFlag.NO_OUT) long size,
			@JniArg(cast = "const gid_t *", flags = { ArgFlag.NO_OUT }) long[] groups);

	// int glfs_set_xlator_option (glfs_t *fs, const char *xlator, const char
	// *key,const char *value) ;

	// typedef void (*glfs_io_cbk) (glfs_fd_t *fd, ssize_t ret, void *data);

	// int glfs_read_async (glfs_fd_t *fd, void *buf, size_t count, int flags,
	// glfs_io_cbk fn, void *data) ;

	// int glfs_write_async (glfs_fd_t *fd, const void *buf, size_t count, int
	// flags, glfs_io_cbk fn, void *data) ;

	// ssize_t glfs_readv (glfs_fd_t *fd, const struct iovec *iov, int
	// iovcnt,int flags) ;

	// ssize_t glfs_writev (glfs_fd_t *fd, const struct iovec *iov, int
	// iovcnt,int flags) ;

	// int glfs_readv_async (glfs_fd_t *fd, const struct iovec *iov, int count,
	// int flags, glfs_io_cbk fn, void *data) ;

	// int glfs_writev_async (glfs_fd_t *fd, const struct iovec *iov, int count,
	// int flags, glfs_io_cbk fn, void *data) ;

	// ssize_t glfs_pread (glfs_fd_t *fd, void *buf, size_t count, off_t
	// offset,int flags) ;

	// ssize_t glfs_pwrite (glfs_fd_t *fd, const void *buf, size_t count, off_t
	// offset, int flags) ;

	// int glfs_pread_async (glfs_fd_t *fd, void *buf, size_t count, off_t
	// offset,int flags, glfs_io_cbk fn, void *data) ;

	// int glfs_pwrite_async (glfs_fd_t *fd, const void *buf, int count, off_t
	// offset,int flags, glfs_io_cbk fn, void *data) ;

	// ssize_t glfs_preadv (glfs_fd_t *fd, const struct iovec *iov, int iovcnt,
	// int count, off_t offset, int flags,glfs_io_cbk fn, void *data) ;

	// ssize_t glfs_pwritev (glfs_fd_t *fd, const struct iovec *iov, int
	// iovcnt,int count, off_t offset, int flags, glfs_io_cbk fn, void *data) ;

	// int glfs_preadv_async (glfs_fd_t *fd, const struct iovec *iov,
	// glfs_io_cbk fn, void *data) ;

	// int glfs_pwritev_async (glfs_fd_t *fd, const struct iovec *iov,
	// glfs_io_cbk fn, void *data) ;

	// int glfs_truncate (glfs_t *fs, const char *path, off_t length) ;
	@JniMethod(cast = "off_t")
	public static final native int glfs_truncate(@JniArg(cast = "glfs_t *") long fd,
			@JniArg(cast = "const char *") String path, @JniArg(cast = "off_t") long length);

	// int glfs_ftruncate (glfs_fd_t *fd, off_t length) ;
	@JniMethod(cast = "off_t")
	public static final native int glfs_ftruncate(@JniArg(cast = "glfs_fd_t *") long fd,
			@JniArg(cast = "off_t") long length);

	// int glfs_ftruncate_async (glfs_fd_t *fd, off_t length, glfs_io_cbk
	// fn,void *data) ;

	// int glfs_fsync_async (glfs_fd_t *fd, glfs_io_cbk fn, void *data) ;

	// int glfs_fdatasync (glfs_fd_t *fd) ;

	// int glfs_fdatasync_async (glfs_fd_t *fd, glfs_io_cbk fn, void *data) ;

	// nu se poate int glfs_mknod (glfs_t *fs, const char *path, mode_t mode,
	// dev_t dev) ;

	// int glfs_link (glfs_t *fs, const char *oldpath, const char *newpath) ;
	@JniMethod()
	public static final native int glfs_link(@JniArg(cast = "glfs_t *") long fd,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String oldpath,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String newpath);

	// int glfs_readdirplus_r (glfs_fd_t *fd, struct stat *stat, struct dirent
	// *dirent, struct dirent **result) ;

	// struct dirent *glfs_readdir (glfs_fd_t *fd) ;

	// struct dirent *glfs_readdirplus (glfs_fd_t *fd, struct stat *stat) ;

	// int glfs_fchmod (glfs_fd_t *fd, mode_t mode) ;

	/**
	 * <code>int glfs_chown (glfs_t *fs, const char *path, uid_t uid, gid_t gid) ;</code>
	 *
	 * @param fd
	 *            file system pointer
	 * @param path
	 *            path
	 * @param uid
	 *            user id
	 * @param gid
	 *            group id
	 * @return -1 on error
	 */
	@JniMethod
	public static final native int glfs_chown(@JniArg(cast = "glfs_t *") long fd,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String path,
			@JniArg(cast = "uid_t", flags = ArgFlag.NO_OUT) long uid,
			@JniArg(cast = "gid_t", flags = ArgFlag.NO_OUT) long gid);

	/**
	 * <code>int glfs_lchown (glfs_t *fs, const char *path, uid_t uid, gid_t gid) ;</code>
	 *
	 * @param fd
	 *            file system pointer
	 * @param path
	 *            link path
	 * @param uid
	 *            user id
	 * @param gid
	 *            group id
	 * @return -1 on error
	 */
	@JniMethod
	public static final native int glfs_lchown(@JniArg(cast = "glfs_t *") long fd,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String path,
			@JniArg(cast = "uid_t", flags = ArgFlag.NO_OUT) long uid,
			@JniArg(cast = "gid_t", flags = ArgFlag.NO_OUT) long gid);

	/**
	 * <code>int glfs_fchown (glfs_fd_t *fd, uid_t uid, gid_t gid)</code>
	 *
	 * @param fd
	 *            file system pointer
	 * @param uid
	 *            user id
	 * @param gid
	 *            group id
	 * @return -1 on error
	 */
	@JniMethod
	public static final native int glfs_fchown(@JniArg(cast = "glfs_fd_t *") long fd,
			@JniArg(cast = "uid_t", flags = ArgFlag.NO_OUT) long uid,
			@JniArg(cast = "gid_t", flags = ArgFlag.NO_OUT) long gid);

	/**
	 * <code>int glfs_utimens (glfs_t *fs, const char *path,struct timespec	times[2])</code>
	 *
	 * @param fd
	 *            file system pointer
	 * @param path
	 *            file path
	 * @param times
	 *            timens[2]
	 * @return 0 success,-1 is retuned and errno is set
	 **/
	@JniMethod()
	public static final native int glfs_utimens(@JniArg(cast = "glfs_t *") long fd,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String path,
			@JniArg(flags = { ArgFlag.NO_OUT }) timespec[] times);

	/**
	 * <code>int glfs_lutimens (glfs_t *fs, const char *path,struct timespec times[2])</code>
	 *
	 * @param fd
	 *            file system pointer
	 * @param path
	 *            file path to link
	 * @param times
	 *            timens[2]
	 * @return 0 success,-1 is retuned and errno is set
	 */
	@JniMethod()
	public static final native int glfs_lutimens(@JniArg(cast = "glfs_t *") long fd,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String path,
			@JniArg(flags = { ArgFlag.NO_OUT }) timespec[] times);

	/**
	 * <code>int glfs_futimens (glfs_fd_t *fs, struct timespec	times[2])</code>
	 *
	 * @param fd
	 *            file system pointer
	 * @param times
	 *            timens[2]
	 * @return 0 success,-1 is retuned and errno is set
	 */
	@JniMethod()
	public static final native int glfs_futimens(@JniArg(cast = "glfs_fd_t *") long fd,
			@JniArg(flags = { ArgFlag.NO_OUT }) timespec[] times);

	// ssize_t glfs_getxattr (glfs_t *fs, const char *path, const char
	// *name,void *value, size_t size) ;
	@JniMethod(cast = "ssize_t")
	public static final native int glfs_getxattr(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path, @JniArg(cast = "const char *") String name,
			@JniArg(cast = "void *", flags = NO_IN) byte[] value, @JniArg(cast = "size_t") long bufsiz);

	// ssize_t glfs_lgetxattr (glfs_t *fs, const char *path, const char
	// *name,void *value, size_t size) ;
	@JniMethod(cast = "ssize_t")
	public static final native int glfs_lgetxattr(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *") String path, @JniArg(cast = "const char *") String name,
			@JniArg(cast = "void *", flags = NO_IN) byte[] value, @JniArg(cast = "size_t") long bufsiz);

	// ssize_t glfs_fgetxattr (glfs_fd_t *fd, const char *name,void *value,
	// size_t size) ;
	@JniMethod(cast = "ssize_t")
	public static final native int glfs_fgetxattr(@JniArg(cast = "glfs_fd_t *") long fs,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String name,
			@JniArg(cast = "void *", flags = NO_IN) byte[] value, @JniArg(cast = "size_t") long size);

	// ssize_t glfs_listxattr (glfs_t *fs, const char *path,void *value, size_t
	// size) ;
	@JniMethod(cast = "ssize_t")
	public static final native int glfs_listxattr(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String path,
			@JniArg(cast = "void *", flags = NO_IN) byte[] value, @JniArg(cast = "size_t") long size);

	// ssize_t glfs_llistxattr (glfs_t *fs, const char *path, void *value,size_t
	// size) ;

	// ssize_t glfs_flistxattr (glfs_fd_t *fd, void *value, size_t size) ;

	// int glfs_setxattr (glfs_t *fs, const char *path, const char *name,const
	// void *value, size_t size, int flags) ;
	@JniMethod
	public static final native int glfs_setxattr(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String path,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String name,
			@JniArg(cast = "void *", flags = ArgFlag.NO_OUT) byte[] value, @JniArg(cast = "size_t") long size,
			int flags);

	// int glfs_lsetxattr (glfs_t *fs, const char *path, const char *name,const
	// void *value, size_t size, int flags) ;

	// int glfs_fsetxattr (glfs_fd_t *fd, const char *name,const void *value,
	// size_t size, int flags) ;

	/**
	 * <code>int glfs_removexattr (glfs_t *fs, const char *path, const char *name) ;</code>
	 *
	 * @param fs
	 *            fs
	 * @param path
	 *            path
	 * @param name
	 *            name
	 * @return -1 on error and erornr is set
	 */
	@JniMethod
	public static final native int glfs_removexattr(@JniArg(cast = "glfs_t *") long fs,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String path,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String name);

	// int glfs_lremovexattr (glfs_t *fs, const char *path, const char *name) ;

	/**
	 * <code>int glfs_fremovexattr (glfs_fd_t *fd, const char *name) ;</code>
	 *
	 * @param fs
	 *            fs
	 * @param name
	 *            name
	 * @return -1 on error.
	 */
	@JniMethod
	public static final native int glfs_fremovexattr(@JniArg(cast = "glfs_fd_t *") long fs,
			@JniArg(cast = "const char *", flags = ArgFlag.NO_OUT) String name);

	// int glfs_fallocate(glfs_fd_t *fd, int keep_size, off_t offset, size_t
	// len) ;

	// int glfs_discard(glfs_fd_t *fd, off_t offset, size_t len) ;

	// int glfs_discard_async (glfs_fd_t *fd, off_t length, size_t lent,
	// glfs_io_cbk fn, void *data) ;

	// int glfs_zerofill(glfs_fd_t *fd, off_t offset, off_t len) ;

	// int glfs_zerofill_async (glfs_fd_t *fd, off_t length, off_t len,
	// glfs_io_cbk fn, void *data) ;

	// char *glfs_getcwd (glfs_t *fs, char *buf, size_t size) ;

	// int glfs_chdir (glfs_t *fs, const char *path) ;

	// int glfs_fchdir (glfs_fd_t *fd) ;

	// char *glfs_realpath (glfs_t *fs, const char *path, char *resolved_path) ;

	// int glfs_posix_lock (glfs_fd_t *fd, int cmd, struct flock *flock) ;

	// glfs_fd_t *glfs_dup (glfs_fd_t *fd) ;

}
