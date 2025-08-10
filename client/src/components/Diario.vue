<template>
    <div class="col-md-4 col-sm-6">
        <b-card class="mt-2" :border-variant="'primary'" align="center">
            <div class="d-flex justify-content-center">
                <b-card-title>Diario {{diario.dia}}</b-card-title>
            </div>
            <b-card-text class="text-left">
                <small><b>Descripción:</b></small><br />
                <h6>{{diario.descripcion}}</h6>
            </b-card-text>
            <hr />
            <b-dropdown text="Acción" variant="info">
                <b-dropdown-item @click="toggleModalEditar()">Editar diario</b-dropdown-item>
                <b-dropdown-divider />
                <b-dropdown-item @click="eliminarD(diario)">Eliminar diario</b-dropdown-item>
            </b-dropdown>
        </b-card>
        <b-modal centered hide-footer v-model="mostrarModalEditar" title="Editar lancha">
            <b-alert v-model="showErrorEditar" variant="danger" dismissible>
                {{errorEditar}}
            </b-alert>
            <form @submit.prevent="submitEditarDiario(diario, nuevaDesc)">
                <b-row>
                    <b-col sm="4">
                        <label for="nom"><b>Descripción</b></label>
                    </b-col>
                    <b-col sm="8">
                        <input v-model="nuevaDesc" type="text" class="form-control" id="nom">
                    </b-col>
                </b-row>
                <div class="d-flex justify-content-end mt-2">
                    <b-button class="m-2" variant="info" type="submit">Editar</b-button>
                    <b-button @click="toggleModalEditar()" class="m-2">Cancelar</b-button>
                </div>
            </form>
        </b-modal>
    </div>
</template>

<script>
    import {mapActions} from 'vuex';
    export default {
        name: "Diario",
        props: {
            diario: Object
        },
        data: () => ({
            mostrarModalEditar: false,
            nuevaDesc: '',
            showErrorEditar: false,
            errorEditar: null
        }),
        methods: {
            ...mapActions(['eliminarDiario', 'actualizarDiario']),
            toggleModalEditar() {
                this.mostrarModalEditar = !this.mostrarModalEditar;
                if (this.mostrarModalEditar) {
                    this.nuevaDesc = this.diario.descripcion;
                }
            },
            eliminarD(diario) {
                this.$swal.fire({
                    title: '¿De verdad desea eliminar el Diario?',
                    text: "¡Esta acción no se puede deshacer!",
                    icon: 'warning',
                    showCancelButton: true,
                    cancelButtonColor: '#dd3333',
                    confirmButtonText: '¡Si, eliminar!',
                    cancelButtonText: 'Cancelar'
                }).then((result) => {
                    if (result.value) {
                        this.eliminarDiario({diario});
                    }
                })
            },
            submitEditarDiario(diario, descripcion) {
                if(!descripcion) {
                    this.showErrorEditar = true;
                    this.errorEditar = 'Debe agregar una descripción.';
                    return;
                }
                this.$swal.fire({
                    title: '¿De verdad desea editar el Diario?',
                    icon: 'warning',
                    showCancelButton: true,
                    cancelButtonColor: '#dd3333',
                    confirmButtonText: '¡Editar!',
                    cancelButtonText: 'Cancelar'
                }).then((res) => {
                    if(res.value) {
                        this.actualizarDiario({diario, descripcion});
                        this.toggleModalEditar();
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>