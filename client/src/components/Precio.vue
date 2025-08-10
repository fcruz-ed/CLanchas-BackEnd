<template>
    <div class="col-md-3 col-sm-6">
        <b-card class="mt-2" :title="'Tiempo: ' + precio.tiempo" border-variant="primary">
            <b-card-text>Precio: ${{precio.precio}}</b-card-text>
            <hr />
            <div class="d-flex justify-content-end">
                <b-button @click="deletePrecio(precio)" variant="danger" size="sm" title="Eliminar precio">Eliminar</b-button>
            </div>
        </b-card>
    </div>
</template>

<script>
    import {mapActions} from 'vuex';
    export default {
        name: "Precio",
        props: {
            precio: Object
        },
        methods: {
            ...mapActions(['eliminarPrecio']),
            deletePrecio(precio) {
                this.$swal.fire({
                    title: '¿Está seguro?',
                    text: "¡Esta acción no se puede deshacer!",
                    icon: 'warning',
                    showCancelButton: true,
                    cancelButtonColor: '#dd3333',
                    confirmButtonText: '¡Si, eliminar!',
                    cancelButtonText: 'Cancelar'
                }).then((result) => {
                    if (result.value) {
                        this.eliminarPrecio({precio});
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>