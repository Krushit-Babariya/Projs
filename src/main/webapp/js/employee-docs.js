$(document).ready(function () {
    $('.viewDoc').click(function (e) {
        e.preventDefault();

        let url = $(this).data('doc-url');
        let type = $(this).data('doc-type');

        let content = '';
        if (type === 'image') {
            content = '<img src="' + url + '" class="img-fluid" />';
        } else if (type === 'pdf') {
            content = '<iframe src="' + url + '" width="100%" height="1000px"></iframe>';
        }

        $('#docModalBody').html(content);
    });
});