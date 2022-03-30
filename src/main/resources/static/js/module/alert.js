// eslint-disable-next-line import/prefer-default-export
export function Alert() {
    Swal.fire({
        title: 'Error!',
        text: 'Do you want to continue',
        icon: 'error',
        confirmButtonText: 'Cool',
    });
}
