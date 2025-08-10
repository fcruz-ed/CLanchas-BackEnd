<template>
    <div>
        <div class="d-flex justify-content-between">
            <h1>Lista de espera</h1>
            <b-button @click="toggleModalEspera()" title="Agregar persona a la lista de espera" variant="info" class="m-2">Agregar persona</b-button>
        </div>

        <table v-if="personasEspera.length" class="table table-hover table-striped text-center mt-4">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(persona, index) in personasEspera" :key="persona.id">
                    <td>{{index}}</td>
                    <td>{{persona.nombre}}</td>
                    <td>
                        <b-button @click="toggleModalEditar(persona)" class="mx-2 text-info" variant="link" title="Editar persona"><i class="fas fa-edit"></i></b-button>
                        <b-button @click="eliminarPersona(persona)" class="mx-2 text-warning" variant="link" title="Eliminar persona"><i class="far fa-trash-alt"></i></b-button>
                    </td>
                </tr>
            </tbody>
        </table>
        <div v-else class="mt-4">
            <h5>No hay personas en la lista de espera.</h5>
        </div>

        <!--        TODO: separar este modal a un componente-->
        <b-modal hide-footer v-model="agregarEsperaModal" title="Agregar persona a la lista de espera" centered>
            <b-alert v-model="showErrorEspera" variant="danger" dismissible>
                {{errorEspera}}
            </b-alert>
            <form @submit.prevent="submitEspera(nombre)">
                <b-row class="mb-2">
                    <b-col sm="3">
                        <label for="nom">Nombre:</label>
                    </b-col>
                    <b-col sm="9">
                        <input v-model="nombre" type="text" required autocomplete="false" placeholder="Juan Perez" class="form-control" id="nom">
                    </b-col>
                </b-row>
                <div class="d-flex justify-content-end">
                    <b-button type="submit" class="m-2" variant="success">Guardar</b-button>
                    <b-button @click="toggleModalEspera()" class="m-2">Cancelar</b-button>
                </div>
            </form>
        </b-modal>
        <b-modal hide-footer v-model="editarPersonaModal" title="Editar persona de la lista de espera" centered>
            <b-alert v-model="showErrorEditar" variant="danger" dismissible>
                {{errorEditar}}
            </b-alert>
            <form @submit.prevent="submitEditarPersona(personaEditar, nombreEditar)">
                <b-row class="mb-2">
                    <b-col sm="3">
                        <label for="nomE">Nombre:</label>
                    </b-col>
                    <b-col sm="9">
                        <input v-model="nombreEditar" type="text" required autocomplete="false" placeholder="Juan Perez" class="form-control" id="nomE">
                    </b-col>
                </b-row>
                <div class="d-flex justify-content-end">
                    <b-button type="submit" class="m-2" variant="info">Editar</b-button>
                    <b-button @click="toggleModalEditar()" class="m-2">Cancelar</b-button>
                </div>
            </form>
        </b-modal>
    </div>
</template>

<script>
    import { mapState, mapActions } from 'vuex';
    export default {
        name: "Espera",
        data: () => ({
            nombre: null,
            agregarEsperaModal: false,
            showErrorEspera: false,
            errorEspera: null,
            editarPersonaModal: false,
            showErrorEditar: false,
            nombreEditar: '',
            errorEditar: null,
            personaEditar: null
        }),
        computed: {
            ...mapState(['personasEspera'])
        },
        methods: {
            ...mapActions(['obtenerListaEspera', 'nuevaPersonaEspera', 'actualizarPersonaListaEspera', 'eliminarPersonaListaEspera']),
            toggleModalEspera() {
                this.agregarEsperaModal = !this.agregarEsperaModal;
            },
            submitEspera(nombre) {
                if(!nombre) {
                    this.showErrorEspera = true;
                    this.errorEspera = 'Tiene que agregar un nombre';
                    return;
                }
                this.nuevaPersonaEspera({ nombre });
                this.toggleModalEspera();
                this.reiniciarModalOcupar();
            },
            reiniciarModalOcupar() {
                this.nombre = null;
            },
            toggleModalEditar(persona) {
                this.editarPersonaModal = !this.editarPersonaModal;
                if(this.editarPersonaModal) {
                    this.nombreEditar = persona.nombre;
                    this.personaEditar = persona;
                }
            },
            submitEditarPersona(persona, nombre) {
                if(!nombre) {
                    this.showErrorEditar = true;
                    this.errorEditar = 'Necesita agregar un nombre';
                    return;
                }
                this.actualizarPersonaListaEspera({persona, nombre});
                this.toggleModalEditar();
            },
            eliminarPersona(persona) {
                this.eliminarPersonaListaEspera({ persona });
            }
        },
        created() {
            this.obtenerListaEspera();
        }
    }
</script>

<style scoped>

</style>