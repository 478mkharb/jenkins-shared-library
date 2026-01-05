def call(String agentLabel, Closure body) {

    def hour = new Date().format(
        'HH',
        TimeZone.getTimeZone('Asia/Kolkata')
    ) as int

    echo "Current Hour (IST): ${hour}"

    if (hour >= 9 && hour < 18) {
        echo "Business hours → Running on agent: ${agentLabel}"

        node(agentLabel) {
            body()
        }

    } else {
        echo "Off hours → Running on controller"

        node('master') {
            body()
        }
    }
}
