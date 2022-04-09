package com.example.helloworld.RunningMatches

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.constraintlayout.solver.widgets.ConstraintAnchor
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.helloworld.R
import kotlinx.android.synthetic.main.rows.view.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class MyAdapter(val context: Context, val opponentList: List<RunningMatchesItem>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //declara variáveis do itemView que mudarão de acordo com a api
        var teamName: TextView
        var teamName2: TextView
        var team1Logo: ImageView
        var team2Logo: ImageView
        var leagueLogo: ImageView
        var leagueName: TextView
        var localTime : TextView
        var matchCard: ConstraintLayout
        var rootView: ConstraintLayout
        var paramsLayout: ConstraintLayout.LayoutParams



        init { //inicia as variaveis com seus respectivos IDs
            teamName = itemView.time1
            teamName2 = itemView.time2
            team1Logo = itemView.findViewById(R.id.team_logo)
            team2Logo = itemView.findViewById(R.id.team_logo2)
            leagueLogo = itemView.findViewById(R.id.league_logo)
            leagueName = itemView.findViewById(R.id.league_seri)
            localTime = itemView.findViewById(R.id.seg_16_00)
            matchCard = itemView.findViewById(R.id.match_card_)
            rootView = itemView.rootView as ConstraintLayout
            paramsLayout = ConstraintLayout.LayoutParams(0,0)

            /*itemView.setOnClickListener{
                val positionAda : Int = bindingAdapterPosition
                Toast.makeText(itemView.context, "clicou no card # ${positionAda + 1}", Toast.LENGTH_SHORT).show()
            }*/

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rows, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        if(opponentList[position].opponents.size == 0){ //se nao existir oponentes dos dois lados (TBD VS TBD) o card é ignorado
            holder.rootView.layoutParams = holder.paramsLayout //faz com que o card desapareça
            holder.matchCard.visibility = View.INVISIBLE //faz o card ficar invisivel
        }else {

            if (opponentList[position].opponents.size > 1) { //se tiver 2 oponentes definidos, os dados da api sao colocados

                //seta nome dos times e suas logos
                holder.teamName2.text = opponentList[position].opponents[1].opponent.name
                holder.teamName.text = opponentList[position].opponents[0].opponent.name

                //seta logos dos times
                Glide.with(context)
                    .load(opponentList[position].opponents[0].opponent.image_url)
                    .into(holder.team1Logo)

                Glide.with(context)
                    .load(opponentList[position].opponents[1].opponent.image_url)
                    .into(holder.team2Logo)
            } else { // se tiver so um oponente (time vs TBD), é colocado TBD em vez do time2
                //seta nomes dos times
                holder.teamName.text = opponentList[position].opponents[0].opponent.name
                holder.teamName2.text = "TBD"
                //seta logo dos times
                holder.team2Logo.setImageResource(R.drawable.tbd)

                Glide.with(context)
                    .load(opponentList[position].opponents[0].opponent.image_url)
                    .into(holder.team1Logo)

            }

            //seta nome e imagem da liga
            Glide.with(context)
                .load(opponentList[position].league.image_url)
                .into(holder.leagueLogo)

            holder.leagueName.text = opponentList[position].league.name

            //se a partida esta ao vivo, muda o card das horas para vermelho AGORA
            if (opponentList[position].status == "running") {
                holder.localTime.text = "AGORA"
                holder.localTime.setBackgroundResource(R.drawable.match_agora)
            } else if (getCurrentDay() == opponentList[position].begin_at.toDate().formatTo("dd")) { //se a partida for no mesmo dia do device, mostra a hoje
                holder.localTime.text =
                    "Hoje, " + opponentList[position].begin_at.toDate().formatTo("HH:mm")
            } else {
                holder.localTime.text = //mostra a data da partida
                    opponentList[position].begin_at.toDate().formatTo("dd.MM HH:mm")
            }
        }

    }

    override fun getItemCount(): Int {
        return opponentList.size
    }
    //funções que convertem a data da api para data do device
    private fun String.toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = timeZone
        return parser.parse(this)
    }

    private fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(this)
    }
    //função que retorna o dia do mes do device
    private fun getCurrentDay(): String {
        val value: Calendar = Calendar.getInstance()
        if(value.get(Calendar.DAY_OF_MONTH) >= 10){
            return value.get(Calendar.DAY_OF_MONTH).toString()
        }else{ //se o dia for menor que 10 retorna com um 0 na frente para melhor conversão
            return "0" + value.get(Calendar.DAY_OF_MONTH).toString()
        }

    }


}