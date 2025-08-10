<template>
    <div>
        <div class="d-flex justify-content-between">
            <h1>Precios/Tiempo</h1>
            <b-button @click="toggleModal()" title="Agregar Precio" variant="info" class="m-2">Agregar precio</b-button>
        </div>
        <b-row>
            <Precio v-for="(precio, indx) in precios" :precio="precio" :key="indx" />
        </b-row>
        <div class="mt-5 text-center" v-if="!precios.length">
            <h4>No hay lanchas para mostrar.</h4>
            <b-button @click="toggleModal()" variant="link">Agregar precio</b-button>
        </div>
<!--        TODO: sacar a un componente-->
        <b-modal hide-footer v-model="agregarPrecioModal" title="Nuevo precio" centered>
            <b-alert v-model="showError" variant="danger" dismissible>
                {{error}}
            </b-alert>
            <form @submit.prevent="sumitPrecio()">
                <b-row class="mb-2">
                    <b-col sm="3">
                        <label for="tiempo">Tiempo</label>
                    </b-col>
                    <b-col sm="9">
                        <input v-model="tiempo" type="text" pattern="^\d{2}:\d{2}:\d{2}$" title="Agregue un formato válido"
                               placeholder="00:15:00" class="form-control" id="tiempo">
                        <small class="row ml-1">Formato: XX:XX:XX</small>
                        <small class="row ml-1">p.e: 00:15:00 (0 horas, 15 minutos y 0 segundos)</small>
                    </b-col>
                </b-row>
                <b-row class="mb-2">
                    <b-col sm="3">
                        <label for="precio">Precio</label>
                    </b-col>
                    <b-col sm="9">
                        <input v-model.number="precio" type="number" min="0.0" class="form-control" id="precio">
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
    import Precio from '@/components/Precio.vue';
    import { mapState, mapActions } from 'vuex';
    export default {
        name: "Precios",
        data() {
            return {
                agregarPrecioModal: false,
                error: null,
                showError: false,
                tiempo: '',
                precio: null
            }
        },
        computed: {
            ...mapState(['precios'])
        },
        methods: {
            ...mapActions(['obtenerPrecios', 'nuevoPrecio']),
            sumitPrecio() {
                if(!this.precio) {
                    this.error = 'No ha agregado el precio.';
                    this.showError = true;
                    return;
                }
                // TODO: Validar formato tiempo (ya está el RegEx en el input)
                let precio = { tiempo: this.tiempo, precio: this.precio };
                this.nuevoPrecio(precio);
                this.toggleModal();
                this.resetNuevoPrecio();
            },
            toggleModal() {
                this.agregarPrecioModal = !this.agregarPrecioModal;
            },
            resetNuevoPrecio() {
                this.tiempo = '';
                this.precio = null;
            }
        },
        created() {
            this.obtenerPrecios();
        },
        components: {
            Precio
        }
    }
</script>

<style scoped>

</style>