<template>
    <div>
        <div class="d-flex justify-content-between">
            <h1>Rentas activas</h1>
            <router-link :to="{name: 'Lanchas'}" title="Nueva renta" class="m-2">Agregar renta</router-link>
        </div>
        <div class="d-flex justify-content-center">
            <h4>Hora del sistema: {{momento}}</h4>
        </div>
        <b-row>
            <Renta v-for="(renta, indx) in rentas" :key="indx" :renta="renta" :usos="usosActivos" :momento="momento" />
        </b-row>
        <div class="mt-5 text-center" v-if="!rentas.length">
            <h4>No hay rentas activas.</h4>
            <router-link :to="{ name: 'Lanchas' }" title="Nueva renta" class="m-2">Agregar renta</router-link>
        </div>
    </div>
</template>

<script>
    import { mapActions, mapState } from 'vuex';
    import moment from 'moment'
    import Renta from '@/components/Renta';
    export default {
        name: "Rentas",
        computed: {
            ...mapState(['rentas', 'precios', 'usosActivos']),
        },
        data () {
            return {
                momento: 'Cargando...'
            }
	    },
        methods: {
            ...mapActions(['obtenerRentas', 'obtenerPrecios', 'obtenerUsosDeRentasActivas']),
            //Tiempo
            startTimer() {
                this.timer = setInterval(() => this.countdown(), 1000);
            },
            countdown() {
                this.momento = moment(new Date()).local().format('HH:mm:ss');
            }
        },
        created() {
            this.obtenerRentas();
            if(!this.precios.length) {
                this.obtenerPrecios();
            }
            this.obtenerUsosDeRentasActivas();
        },
        mounted(){
            this.startTimer();
        },
        components: {
            Renta
        }
    }
</script>

<style scoped>

</style>