/**
 * 监听事件，手机切换横屏/竖屏时触发
 */
window.addEventListener('orientationchange', function(event) {
	if (window.orientation == 180 || window.orientation == 0) {
		// alert("竖屏");
		$("#zhezhao").fadeOut(400);
	}
	if (window.orientation == 90 || window.orientation == -90) {
		// alert("横屏");
		$("#zhezhao").fadeIn(400);
	}
});