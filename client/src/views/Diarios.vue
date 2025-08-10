<template>
    <div>
        <div class="d-flex justify-content-between">
            <h1>Diarios</h1>
            <b-button :disabled="!checDiarioDia" @click="toggleModal()" title="Agregar diario" variant="info" class="m-2">Nuevo diario</b-button>
        </div>
        <b-row>
            <Diario v-for="(diario, indx) in diarios"
                    :diario="diario"
                    :key="indx" />
        </b-row>
        <b-modal hide-footer v-model="agregarDiarioModal" :title="'Nuevo diario día: '+diaActual" centered>
            <b-alert v-model="showError" variant="danger" dismissible>
                {{errorModal}}
            </b-alert>
            <form @submit.prevent="submitDiario(diarioDesc)">
                <b-row class="mb-2">
                    <b-col sm="3">
                        <label for="desc">Descripción:</label>
                    </b-col>
                    <b-col sm="9">
                        <input v-model="diarioDesc" type="text" class="form-control" id="desc">
                    </b-col>
                </b-row>
                <div class="d-flex justify-content-end">
                    <b-button type="submit" class="m-2" variant="success">Enviar</b-button>
                    <b-button @click="toggleModal()" class="m-2">Cancelar</b-button>
                </div>
            </form>
        </b-modal>
    </div>
</template>

<script>
    import Diario from "../components/Diario";
    import { mapState, mapActions } from 'vuex'
    import moment from 'moment'
    export default {
        name: "Diarios",
        data: () => ({
            diaActual: '',
            showError: false,
            errorModal: null,
            agregarDiarioModal: false,
            diarioDesc: null
        }),
        methods: {
            ...mapActions(['obtenerDiarios', 'nuevoDiario']),
            submitDiario(descripcion) {
                if (!descripcion) {
                    this.showError = true;
                    this.errorModal = 'Debe agregar una descripción'
                    return;
                }
                let diario = { dia: this.diaActual, descripcion };
                this.nuevoDiario(diario);
                this.toggleModal();
                this.reiniciarModal();
            },
            toggleModal() {
                this.agregarDiarioModal = !this.agregarDiarioModal;
            },
            reiniciarModal() {
                this.diarioDesc = null;
            }
        },
        computed: {
            ...mapState(['diarios']),
            checDiarioDia() {
                for (let diario of this.diarios) {
                    if (diario.dia === this.diaActual) {
                        return false;
                    }
                }
                return true;
            }
        },
        components: {
            Diario
        },
        created() {
            if(!this.diarios.length) {
                this.obtenerDiarios();
            }
            this.diaActual = moment(new Date()).format("DD/MM/YYYY");
        },
    }
</script>

<style scoped>

</style>