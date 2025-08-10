<template>
    <div>
        <div class="d-flex justify-content-between">
            <h1>Lanchas</h1>
            <b-button @click="toggleModal()" title="Agregar lancha" variant="info" class="m-2">Agregar lancha</b-button>
        </div>
        <b-row>
            <Lancha v-for="(lancha, indx) in lanchas"
                    :lancha="lancha"
                    :key="indx"
                    :usos="usosActivos"
                    :momento="momento" />
        </b-row>
        <div class="mt-5 text-center" v-if="!lanchas.length && !error">
            <h4>No hay lanchas para mostrar.</h4>
            <b-button @click="toggleModal()" variant="link">Agregar lancha</b-button>
        </div>
        <div class="mt-5 text-danger text-center" v-if="error">
            <h4>Error de conexión al servidor, compruebe las conexiones.</h4>
        </div>
<!--        TODO: separar este modal a un componente-->
        <b-modal hide-footer v-model="agregarLanchaModal" title="Nueva lancha" centered>
            <b-alert v-model="showError" variant="danger" dismissible>
                {{errorModal}}
            </b-alert>
            <form @submit.prevent="submitLancha()">
                <b-row class="mb-2">
                    <b-col sm="3">
                        <label for="numero">Número:</label>
                    </b-col>
                    <b-col sm="9">
                        <input v-model.number="numero" type="number" min="0" max="100" class="form-control" id="numero">
                    </b-col>
                </b-row>
                <b-row class="mb-2">
                    <b-col sm="3">
                        <label for="nombre">Nombre:</label>
                    </b-col>
                    <b-col sm="9">
                        <input v-model="nombre" type="text" class="form-control" id="nombre">
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
    import Lancha from '@/components/Lancha.vue';
    import { mapState, mapActions } from 'vuex';
    import moment from 'moment'

    export default {
        name: "Lanchas",
        data() {
            return {
                agregarLanchaModal: false,
                numero: null,
                nombre: '',
                errorModal: null,
                showError: false,
                momento: '',
                timer: ''
            }
        },
        methods: {
            ...mapActions(['getLanchas', 'nuevaLancha', 'obtenerPrecios', 'obtenerUsosDeRentasActivas']),
            submitLancha() {
                if(!this.numero) {
                    this.errorModal = 'No ha ingresado un número';
                    this.showError = true;
                    return;
                }
                let lancha = { numero: this.numero, nombre: this.nombre }
                this.nuevaLancha(lancha);
                this.toggleModal();
                this.resetNuevaLancha();
            },
            toggleModal() {
                this.agregarLanchaModal = !this.agregarLanchaModal;
            },
            resetNuevaLancha() {
                this.numero = null;
                this.nombre = '';
            },
            //Tiempo
            startTimer() {
                this.timer = setInterval(() => this.countdown(), 1000);
            },
            countdown() {
                this.momento = moment(new Date()).local().format('HH:mm:ss')
            }
        },
        computed: {
            ...mapState(['lanchas', 'precios', 'usosActivos', 'error'])
        },
        created() {
            this.getLanchas();
            if (!this.precios.length) {
                this.obtenerPrecios();
            }
            if(!this.usosActivos.length) {
                this.obtenerUsosDeRentasActivas();
            }
        },
        mounted(){
            this.startTimer()
        },
        components: {
            Lancha
        }
    }
</script>

<style scoped>
</style>