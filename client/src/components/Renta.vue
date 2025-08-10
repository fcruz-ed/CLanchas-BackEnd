<template>
    <div class="col-md-6 col-sm-12">
        <b-card border-variant="primary" class="mt-2">
            <b-card-title align="center">Renta de Lancha #{{renta.lancha.numero}}</b-card-title>
            <div class="d-flex justify-content-between">
                <small class="text-uppercase">Día</small>
                <h4>{{renta.fecha}}</h4>
            </div>
            <div class="d-flex justify-content-between">
                <small class="text-uppercase">Hora de inicio</small>
                <h4>{{new Date(renta.renta_de).toLocaleTimeString()}}</h4>
            </div>
            <hr />
            <div class="d-flex justify-content-between">
                <small class="text-uppercase mt-2">Tiempo Comprado:</small>
                <h4 class="text-uppercase mt-2">{{tiempo}}</h4>
            </div>
            <hr />
            <div class="d-flex justify-content-between">
                <small class="text-uppercase mt-2">Tiempo Restante:</small>
                <h4 :class="[(restarTiempo(momento) === '00:00:00') ? 'text-danger' : (tresMinutos(restarTiempo(momento))) ?  'text-warning' :'text-success', 'mt-2']">{{restarTiempo(momento)}}</h4>
            </div>
            <p class=""></p>
            <hr />
            <div class="d-flex justify-content-between">
                <small class="text-uppercase mt-2">Adultos:</small><h4>{{renta.c_adultos}}</h4>
            </div>
            <div class="d-flex justify-content-between">
                <small class="text-uppercase mt-2">Jovenes:</small><h4>{{renta.c_jovenes}}</h4>
            </div>
            <hr />
            <small class="text-uppercase">Observaciones:</small>
            <b-card-text v-if="renta.observaciones === ''" class="text-uppercase">No hay observaciones</b-card-text>
            <b-card-text v-else>{{renta.observaciones}}</b-card-text>
            <hr />
            <div class="d-flex justify-content-between">
                <b-button @click="removeRenta(renta)" variant="warning">Terminar renta</b-button>
                <b-dropdown text="Acción" right variant="info">
                    <b-dropdown-item @click="toggleModalTiempo()">Agregar tiempo</b-dropdown-item>
                    <b-dropdown-divider />
                    <b-dropdown-item @click="toggleModalEditar()">Editar renta</b-dropdown-item>
                </b-dropdown>
<!--                <b-button @click="toggleModalTiempo()" variant="info">Agregar tiempo</b-button>-->
            </div>
        </b-card>
        <b-modal hide-footer v-model="agregarTiempoModal" title="Agregar tiempo" centered>
            <b-alert v-model="showErrorTiempo" variant="danger" dismissible>
                {{errorTiempo}}
            </b-alert>
            <form @submit.prevent="addTiempo(renta, precioSeleccionado)">
                <b-row>
                    <b-col sm="4">
                        <label for="time"><b>Tiempo:</b></label>
                    </b-col>
                    <b-col sm="8">
                        <b-form-select id="time" v-model="precioSeleccionado">
                            <template slot="first">
                                <b-form-select-option :value="null" disabled>-- Seleccione un Precio --</b-form-select-option>
                            </template>
                            <b-form-select-option v-for="(precio, indx) in precios" :value="precio" :key="indx">
                                {{precio.tiempo + ' - $' + precio.precio}}
                            </b-form-select-option>
                        </b-form-select>
                    </b-col>
                </b-row>
                <div class="d-flex justify-content-end mt-2">
                    <b-button class="m-2" variant="success" type="submit">Enviar</b-button>
                    <b-button @click="toggleModalTiempo()" class="m-2">Cancelar</b-button>
                </div>
            </form>
        </b-modal>
        <b-modal hide-footer v-model="editarRentaModal" title="Editar datos de la renta" centered>
            <b-alert v-model="showErrorEditar" variant="danger" dismissible>
                {{errorEditar}}
            </b-alert>
            <form @submit.prevent="editarRenta(renta, cant_adult, cant_jov, observaciones)">
                <b-row>
                    <b-col sm="4">
                        <label for="ca"><b>Cantidad de Adultos</b></label>
                    </b-col>
                    <b-col sm="8">
                        <input v-model.number="cant_adult" type="number" min="0" max="10" class="form-control" id="ca">
                    </b-col>
                </b-row>
                <b-row>
                    <b-col sm="4">
                        <label for="cj"><b>Cantidad de Jovenes</b></label>
                    </b-col>
                    <b-col sm="8">
                        <input v-model.number="cant_jov" type="number" min="0" max="10" class="form-control" id="cj">
                    </b-col>
                </b-row>
                <b-row>
                    <b-col sm="4">
                        <label for="to"><b>Observaciones</b></label>
                    </b-col>
                    <b-col sm="8">
                        <b-form-textarea v-model="observaciones" id="to" placeholder="Observaciones de la renta"></b-form-textarea>
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
    import { mapState, mapActions } from 'vuex';
    import moment from 'moment'
    export default {
        name: "Renta",
        data() {
            return {
                errorTiempo: null,
                showErrorTiempo: false,
                errorEditar: null,
                showErrorEditar: false,
                agregarTiempoModal: false,
                editarRentaModal: false,
                precioSeleccionado: null,
                tiempo: '00:00:00',
                cant_adult: null,
                cant_jov: null,
                observaciones: ''
            }
        },
        props: {
            renta: Object,
            usos: Array,
            momento: ''
        },
        computed: {
            ...mapState(['precios'])
        },
        methods: {
            ...mapActions(['terminarRenta', 'nuevoUso', 'actualizarRenta']),
            removeRenta(renta) {
                this.$swal.fire({
                    title: '¿De verdad desea terminar la Renta?',
                    html: `¡Esta acción no se puede deshacer!<br /><hr />`+this.obtenerTiempoPrecioFinal(),
                    icon: 'warning',
                    showCancelButton: true,
                    cancelButtonColor: '#dd3333',
                    confirmButtonText: '¡Si, terminar!',
                    cancelButtonText: 'Cancelar'
                }).then((result) => {
                    if(result.value) {
                        this.terminarRenta({renta, en_uso: false});
                    }
                })
            },
            addTiempo(renta, precio) {
                if(!precio) {
                    this.errorTiempo = 'No ha seleccionado un tiempo a agregar, elija uno.'
                    this.showErrorTiempo = true;
                    return;
                }
                this.nuevoUso({renta_id: renta.id, tiempo: precio.tiempo, precio: precio.precio});
                this.toggleModalTiempo();
                this.reiniciarModal();
                this.sumarTiempoGeneral(precio.tiempo)
            },
            editarRenta(renta, c_adultos, c_jovenes, observaciones) {
                if(!c_adultos) {
                    this.showErrorEditar = true;
                    this.errorEditar = 'Debe agregar la cantidad de adultos';
                    return;
                }
                if(!c_jovenes) {
                    this.showErrorEditar = true;
                    this.errorEditar = 'Debe agregar la cantidad de jovenes';
                    return;
                }
                this.$swal.fire({
                    title: '¿De verdad desea editar la Renta?',
                    icon: 'warning',
                    showCancelButton: true,
                    cancelButtonColor: '#dd3333',
                    confirmButtonText: '¡Aceptar!',
                    cancelButtonText: 'Cancelar'
                }).then((res) => {
                    if(res.value) {
                        this.actualizarRenta({renta, c_adultos, c_jovenes, observaciones});
                        this.toggleModalEditar();
                    }
                });
            },
            toggleModalEditar() {
                this.editarRentaModal = !this.editarRentaModal;
                if(this.editarRentaModal) {
                    this.cant_adult = this.renta.c_adultos;
                    this.cant_jov = this.renta.c_jovenes;
                    this.observaciones = this.renta.observaciones;
                }
            },
            toggleModalTiempo() {
                this.agregarTiempoModal = !this.agregarTiempoModal;
            },
            reiniciarModal() {
                this.precioSeleccionado = null;
            },
            sumarTiempoGeneral(tiempo) {
                let tTiempo = moment(this.tiempo, 'HH:mm:ss')
                        .add(tiempo.split(":")[2], 'seconds')
                        .add(tiempo.split(":")[1], 'minutes')
                        .add(tiempo.split(":")[0], 'hours')
                this.tiempo = tTiempo.format("HH:mm:ss");
            },
            sumarTiempo() {
                let tTiempo = moment("00:00:00", 'HH:mm:ss');
                for(let i in this.usos){
                    if(this.usos[i].renta_id === this.renta.id) {
                        tTiempo = moment(tTiempo, 'HH:mm:ss')
                            .add(this.usos[i].tiempo.split(":")[2], 'seconds')
                            .add(this.usos[i].tiempo.split(":")[1], 'minutes')
                            .add(this.usos[i].tiempo.split(":")[0], 'hours');
                    }
                }
                this.tiempo = tTiempo.format("HH:mm:ss");
            },
            restarTiempo(m) {
                let hi = new Date(this.renta.renta_de).toLocaleTimeString() //Hora inicio para restar
                let dif1 = moment.utc(moment(m,"HH:mm:ss").diff(moment(hi,"HH:mm:ss"))).format("HH:mm:ss") //Diferencia entre Hora sistema y Hora inicio
                let dif2 = moment.utc(moment(this.tiempo,"HH:mm:ss").diff(moment(dif1,"HH:mm:ss"))).format("HH:mm:ss") //Diferencia entre tiempo y dif1
                // Diff dias
                if (new Date(this.renta.renta_de).getDate() < new Date().getDate()) {
                    dif2 = '00:00:00';
                }
                if(dif2.split(':')[0] === '23' || dif2.split(':')[0] === '22' || dif2.split(':')[0] === '21') {
                    dif2 = '00:00:00';
                }
                if(dif2 === 'Invalid date') {
                    dif2 = 'Cargando...';
                }
                return dif2;
            },
            tresMinutos(dif) {
                let tTiempo = moment('00:00:00', 'HH:mm:ss')
                    .add(dif.split(':')[2], 'seconds')
                    .add(dif.split(':')[1], 'minutes')
                    .add(dif.split(":")[0], 'hours');
                return tTiempo.minutes() < 3;
            },
            obtenerTiempoPrecioFinal() {
                let resumen = '<h4>Timpo - Precio</h4>';
                let precioFinal = 0.0;
                this.usos.forEach(uso => {
                    if(uso.renta_id === this.renta.id) {
                        resumen += `${uso.tiempo} - $${uso.precio}<br />`;
                        precioFinal += uso.precio;
                    }
                });
                resumen += `<br /><h4>Precio final: $${precioFinal}</h4>`;
                return resumen;
            }
        },
        beforeUpdate() {
            this.sumarTiempo();
        }
    }
</script>

<style scoped>

</style>