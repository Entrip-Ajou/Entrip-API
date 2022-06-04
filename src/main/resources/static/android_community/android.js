function showAndroid(getUserId) {
    Android.showToast(getUserId);
}

document.addEventListener('DOMContentLoaded', () => {
    showAndroid('Hello Android!')

});