import { useEffect, useState } from "react";
import "./App.css";
import toast, { Toaster } from "react-hot-toast";

export default function App() {

  const [users, setUsers] = useState([]);
  const [form, setForm] = useState({
    name: "",
    email: "",
    college: ""
  });
  const [loading, setLoading] = useState(false);

  const API = "http://localhost:8080/api";

  // Fetch users
  const fetchUsers = async () => {
    try {
      const res = await fetch(`${API}/users`);
      const data = await res.json();
      setUsers(data);
    } catch (err) {
  toast.error(err.message);
}
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  // Register user
  const handleSubmit = async () => {
    if (!form.name || !form.email || !form.college) {
      toast.error("Please fill all fields");
      return;
    }

    setLoading(true);

    try {
      const res = await fetch(`${API}/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(form)
      });

      if (!res.ok) throw new Error("Registration failed");

      const data = await res.json();

      toast.success("User Registered!");
      toast("UTM: " + data.utm.utmLink);

      setForm({ name: "", email: "", college: "" });
      fetchUsers();

    } catch (err) {
      toast.error(err.message);
    } finally {
      setLoading(false);
    }
  };

  // Copy UTM
  const copyUTM = (link) => {
    navigator.clipboard.writeText(link);
    toast.success("UTM Copied!");
  };

  return (
    <div className="container">

      <Toaster position="top-right" />

      <h1>🚀 IAC Dashboard</h1>

      {/* REGISTER CARD */}
      <div className="card">
        <h2>Register User</h2>

        <input
          placeholder="Name"
          value={form.name}
          onChange={(e) => setForm({ ...form, name: e.target.value })}
        />

        <input
          placeholder="Email"
          value={form.email}
          onChange={(e) => setForm({ ...form, email: e.target.value })}
        />

        <input
          placeholder="College"
          value={form.college}
          onChange={(e) => setForm({ ...form, college: e.target.value })}
        />

        <button onClick={handleSubmit} disabled={loading}>
          {loading ? "Registering..." : "Register"}
        </button>
      </div>

      {/* USERS TABLE */}
      <div className="card">
        <h2>Users</h2>

        {users.length === 0 ? (
          <p>No users yet</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Email</th>
                <th>College</th>
                <th>UTM</th>
                <th>Clicks</th>
                <th>Signups</th>
              </tr>
            </thead>

            <tbody>
              {users.map((user) => (
                <tr key={user.id}>
                  <td>{user.name}</td>
                  <td>{user.email}</td>
                  <td>{user.college}</td>
                  <td>{user.tracking?.clicks}</td>
                  <td>{user.tracking?.signups}</td>
                  <td>
                    <a
                      href={user.utm?.utmLink}
                      target="_blank"
                      rel="noreferrer"
                    >
                      Open
                    </a>
                    <br />
                    <button onClick={() => copyUTM(user.utm?.utmLink)}>
                      Copy
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

    </div>
  );
}