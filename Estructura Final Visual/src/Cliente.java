public class Cliente {
    private TipoTramite tipoTramite;

    public Cliente(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    // Getter y setter

    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
}